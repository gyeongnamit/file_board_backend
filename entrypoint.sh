#!/bin/bash

echo "Enter DB Url:"
read DB_URL

echo "Enter DB Username:"
read DB_USERNAME

echo "Enter DB Password:"
read DB_PASSWORD  

echo "Enter access key:"
read ACCESS_KEY  

echo "Enter secret key:"
read SECRET_KEY  

echo "Enter bucket name:"
read BUCKET_NAME

# 입력받은 값을 환경 변수로 설정
export DB_URL
export DB_USERNAME
export DB_PASSWORD
export ACCESS_KEY
export SECRET_KEY
export BUCKET_NAME

#프로그램 실행
exec java -jar /app/app.war