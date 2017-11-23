# cloud-employeeslistapp

# Application links in SCP CF

https://p1943057769trial-trial-dev-employeeslist-ui.cfapps.eu10.hana.ondemand.com/index.html

https://p1943057769trial-trial-dev-employeeslist-java.cfapps.eu10.hana.ondemand.com/core/employees
https://p1943057769trial-trial-dev-employeeslist-java.cfapps.eu10.hana.ondemand.com/core/test

# Blogs and tutorials about this scenario
https://blogs.sap.com/2015/12/08/sap-hana-sps-11-new-developer-features-hdi/

https://help.sap.com/doc/4505d0bdaf4948449b7f7379d24d0f0d/2.0.01/en-US/b3092cdd8e754a08a9e86006a53c4cca.html

https://www.sap.com/developer/tutorials/xsa-hdi-module.html

# CF commands
java -jar mta.jar -build-target=CF build

cf deploy cloud-employeeslistapp-tomee.mtar

cf allow-space-ssh SPACE_NAME
cf enable-ssh APP_NAME

cf security-groups
cf security-group SECURITY_GROUP

cf ssh -L localhost:30015:<host>:<port> <application_name> -N

cf m  
cf cs application-logs lite app-logs  
cf bs employeeslist-java app-logs 
cf restage employeeslist-java

https://docs.cloudfoundry.org/devguide/deploy-apps/ssh-apps.html

C:\Users\fabiano.a.rosa>cf curl /v2/info
{
   "name": "",
   "build": "",
   "support": "https://help.cf.eu10.hana.ondemand.com",
   "version": 0,
   "description": "Cloud Foundry at SAP Cloud Platform",
   "authorization_endpoint": "https://login.cf.eu10.hana.ondemand.com",
   "token_endpoint": "https://uaa.cf.eu10.hana.ondemand.com",
   "min_cli_version": null,
   "min_recommended_cli_version": null,
   "api_version": "2.98.0",
   "app_ssh_endpoint": "ssh.cf.eu10.hana.ondemand.com:2222",
   "app_ssh_host_key_fingerprint": "f3:12:47:b5:3a:19:6e:6c:4e:9d:90:2e:6f:8e:87:cc",
   "app_ssh_oauth_client": "ssh-proxy",
   "doppler_logging_endpoint": "wss://doppler.cf.eu10.hana.ondemand.com:443"
}


ssh -p 2222 cf:f3:12:47:b5:3a:19:6e:6c:4e:9d:90:2e:6f:8e:87:cc/0@ssh.cf.eu10.hana.ondemand.com


