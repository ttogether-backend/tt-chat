#!/usr/bin/bash
profile="$1"
image_name="$2"

./mvnw clean install -P dev 
if [ $? -eq 0 ]; then
	echo "Maven 빌드 성공, 도커 이미지를 생성하고 도커 허브에 push 합니다."
	docker login
	if [ $? -eq 0 ]; then
		docker buildx create --name multi-platform-build --driver docker-container --bootstrap --use
		docker buildx build --platform=linux/amd64,linux/arm64 -t "$image_name" --push .
	else
		echo "도커 로그인 실패"
	fi
else
	echo "Maven 빌드 실패!"
fi
