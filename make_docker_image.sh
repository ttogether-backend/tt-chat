#!/usr/bin/bash
image_name="$1"
./mvnw clean install
if [ $? -eq 0 ]; then
	echo "Maven 빌드 성공, 도커 이미지를 생성합니다."
	docker buildx build --platform=linux/amd64,linux/arm64 -t "$image_name" --load .
else
	echo "Maven 빌드 실패!"
fi
