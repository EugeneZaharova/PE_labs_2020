run:
	sudo docker-compose up -d

stop:
	sudo docker-compose down

restart: stop run
