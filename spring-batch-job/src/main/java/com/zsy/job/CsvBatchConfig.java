package com.zsy.job;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import com.zsy.domain.Emp;
import com.zsy.validate.CsvBeanValidator;
import com.zsy.validate.CsvItemProcessor;

@Configuration
@EnableBatchProcessing // 开启batch
public class CsvBatchConfig {
	@Bean
	public ItemReader<Emp> reader(DataSource dataSource) {
		/*
		 * List<Emp> list = new ArrayList<Emp>(); Emp emp = null; for (int i =
		 * 0; i < 100; i++) { emp = new Emp();
		 * emp.setEmpno(Long.valueOf(String.valueOf(i))); emp.setEname("测试" +
		 * i); }
		 */

		JdbcCursorItemReader<Emp> reader = new JdbcCursorItemReader<Emp>();
		reader.setDataSource(dataSource);
		reader.setSql("select  * from EMp ");
		reader.setRowMapper(new RowMapper<Emp>() {

			@Override
			public Emp mapRow(ResultSet rs, int arg1) throws SQLException {
				Emp emp = new Emp();
				emp.setEmpno(rs.getLong("EMPNO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setJob(rs.getString("JOB"));
				return emp;
			}
		});

		return reader;

	}

	@Bean
	public ItemProcessor<Emp, Emp> processor() {
		CsvItemProcessor processor = new CsvItemProcessor(); // 使用自定义的ItemProcessor的实现
		processor.setValidator(csvBeanValidator()); // 为Processor指定校验器
		return processor;
	}

	@Bean
	public Validator<Emp> csvBeanValidator() {
		return new CsvBeanValidator<Emp>();
	}

	/**
	 * 写到数据库
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public ItemWriter<Emp> writer(DataSource dataSource) {// Spring能让容器中已有的Bean以参数的形式注入，Springboot已经定义了DataSource
		JdbcBatchItemWriter<Emp> writer = new JdbcBatchItemWriter<Emp>();// 使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
		writer.setDataSource(dataSource);
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Emp>());
		String sql = "insert into emp (empno,ename) values(hibernate_sequence.nextval, :ename)";// 在此设置要执行批处理的sql语句
		writer.setSql(sql);
		return writer;
	}

	@Bean
	public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		JobRepositoryFactoryBean bean = new JobRepositoryFactoryBean();
		bean.setDatabaseType("oracle");
		bean.setTransactionManager(transactionManager);
		bean.setDataSource(dataSource);
		bean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		return bean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
			throws Exception {
		SimpleJobLauncher job = new SimpleJobLauncher();
		job.setJobRepository(jobRepository(dataSource, transactionManager));
		return job;
	}

	@Bean
	public Job importJob(JobBuilderFactory jobs, Step s) {
		return jobs.get("importJob").incrementer(new RunIdIncrementer()).flow(s) // 指定step
				.end().listener(csvJobListener())// 绑定监听器
				.build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Emp> reader, ItemWriter<Emp> writer,
			ItemProcessor<Emp, Emp> processor) {
		return stepBuilderFactory.get("step1").<Emp, Emp> chunk(65000) // 批处理每次提交65000条数据
				.reader(reader) // 给step绑定reader
				.processor(processor) // 给step绑定Processor
				.writer(writer) // 给step绑定writer
				.build();
	}

	@Bean
	public CsvJobListener csvJobListener() {
		return new CsvJobListener();
	}
}
