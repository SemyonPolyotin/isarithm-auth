# Isarithm Auth
Authorization Java Spring REST web service for Isarithm

## Installation
```bash
    $ git clone https://github.com/matthewpoletin/isarithm-auth.git
    $ cd isarithm-auth
    $ gradle bootJar
    $ scp ./build/libs/isarithm-auth.jar isarithm@vds1.isarithm.ru:/var/www/isarithm/isarithm-auth.jar
    $ ssh -f isarithm@vds1.isarithm.ru 'nohup java -Dserver.port=4002 -jar /var/www/isarithm/isarithm-auth.jar --spring.profiles.active=prod >/dev/null 2>&1 &'
```

## Restart
```fish
    ssh -f isarithm@vds1.isarithm.ru 'kill -15 (ps aux | grep 'isarithm-auth.jar' | grep -v grep | awk \'{print $2}\')'
```