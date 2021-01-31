#!/bin/bash
nohup java -jar pixivapi.jar >> start.out 2>&1 &
echo "starting，you can check the start.out"