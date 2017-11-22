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

cf security-groups
cf security-group hana_trial_HCPCM-432
cf ssh -L localhost:30015:10.253.93.93:30041 employeeslist-db -N

cf m  
cf cs application-logs lite app-logs  
cf bs employeeslist-java app-logs 
cf restage employeeslist-java