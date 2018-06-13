# codingtest

To compile app, download it and run
> mvn package

from app's folder.

To start app, run

> java -jar target\paysafetest-1.0-SNAPSHOT.jar

## Testing:

#### To start watching:
  METHOD PUT
  
  \<your_host\>/monitor/start
  
  Content-Type: application/json
  
  BODY
  
  {
  "uri": "\<tested_server\>",
  "seconds": \<interval_in_seconds\>
  }
 
#### To stop watching:
  METHOD PUT
  
  \<your_host\>/monitor/stop
  
  Content-Type: application/json
  
  BODY
  
  {
  "uri": "\<tested_server\>"
  }
  
#### To get information:
  METHOD PUT
  
  \<your_host\>/monitor/info
  
  Content-Type: application/json
  
  BODY
  
  {
  "uri": "\<tested_server\>"
  }
