# jstore
This is a demo to start springboot application with mysql in k8s cluster.

## how to start this application
1) Install k8s in local machine.
2) docker pull registry and run this docker with port 5000.
3) run kubectl apply mysql-single.yaml to create mysql database instance.
4) execute dbscript.
5) run deploy.bat
6) visit http://localhost:30080/api/v1/health/status, the expected result should be
  <pre>
  {
   "success": true,
   "data": "OK",
   "errorCode": null,
   "errorMsg": null
   }
 </pre>

## directory tree
<pre>
└─src
    ├─main
    │  ├─dbscript
    │  ├─docker
    │  ├─java
    │  │  └─com
    │  │      └─raybyte
    │  │          └─jstore
    │  │              ├─controller
    │  │              ├─dto
    │  │              ├─entity
    │  │              ├─repository
    │  │              └─utils
    │  ├─k8s
    │  └─resources
    │      ├─docker
    │      ├─static
    │      └─templates
    └─test
        └─java
            └─com
                └─raybyte
                    └─jstore
</pre>