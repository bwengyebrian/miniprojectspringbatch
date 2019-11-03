FROM openjdk:8
COPY ./target/springbatch610158-0.1.jar springbatch610158-0.1.jar
CMD ["java","-jar","springbatch610158-0.1.jar"]