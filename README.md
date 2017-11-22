# cloud-employeeslistapp

https://p1943057769trial-trial-dev-employeeslist-ui.cfapps.eu10.hana.ondemand.com/index.html

https://p1943057769trial-trial-dev-employeeslist-java.cfapps.eu10.hana.ondemand.com/core/employees
https://p1943057769trial-trial-dev-employeeslist-java.cfapps.eu10.hana.ondemand.com/core/test

https://blogs.sap.com/2015/12/08/sap-hana-sps-11-new-developer-features-hdi/

https://www.sap.com/developer/tutorials/xsa-hdi-module.html

java -jar mta.jar -build-target=CF build

cf deploy cloud-employeeslistapp-tomee.mtar

cf security-groups
cf security-group hana_trial_HCPCM-432
cf ssh -L localhost:30015:10.253.93.93:30041 employeeslist-db -N