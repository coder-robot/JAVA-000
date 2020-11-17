# spring boot 打普通jar包
```shell script
mvn clean package -DskipTests -Dspring-boot.repackage.skip=true

mvn clean install -DskipTests -Dspring-boot.repackage.skip=true
```
