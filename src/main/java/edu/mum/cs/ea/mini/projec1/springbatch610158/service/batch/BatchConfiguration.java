package edu.mum.cs.ea.mini.projec1.springbatch610158.service.batch;

import edu.mum.cs.ea.mini.projec1.springbatch610158.model.Student;
import edu.mum.cs.ea.mini.projec1.springbatch610158.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    StudentRepository studentRepository;

    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("personItemReader")
                .resource(new ClassPathResource("studentdata.csv"))
                .delimited()
                .names(new String[]{"studentNumber","firstName", "lastName","age"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {{
                    setTargetType(Student.class);
                }})
                .build();
    }

    @Bean
    public StudentItemProcessor processor() {
        return new StudentItemProcessor();
    }

    @Bean
    public JpaItemWriter<Student> writer(){
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
//    public JdbcBatchItemWriter<Student> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Student>().e
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO students (first_name, last_name) VALUES (:firstName, :lastName)")
//                .dataSource(dataSource)
//                .build();
//    }

    @Bean(name = "studentJob")
    public Job importUserJob( Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1)
                .end()
                .build();

//        return jobBuilderFactory.get("processJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener())
//                .flow(orderStep1())
//                .end()
//                .build();
    }

    @Bean
    public JobExecutionListener listener() {

        return new JobCompletionListener();
    }

    @Bean
    public Step step1(JpaItemWriter<Student> writer) {
        return stepBuilderFactory.get("step1")
                .<Student, Student> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();

    }


}