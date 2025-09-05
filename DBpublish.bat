@REM SQLPackage /Action:Publish /SourceFile:".\DB\DB\bin\Release\DB.dacpac" /TargetServerName:"(local)\MSSQLSERVER2019" /TargetDatabaseName:"ShopShpere" /p:GenerateSmartDefaults=True /p:DropConstraintsNotInSource=True /p:BlockOnPossibleDataLoss=True /p:DropObjectsNotInSource=True /p:ExcludeObjectTypes=Users;Logins;RoleMembership;Permissions
@echo off
REM ---- DBPublish.bat ----
REM Path to sqlpackage.exe (comes with SQL Server or VS)
set SQLPACKAGE_PATH="C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\Common7\IDE\Extensions\Microsoft\SQLDB\DAC\150\sqlpackage.exe"


REM Path to your .dacpac file
set DACPAC_PATH=".\DB\DB\DB\bin\Release\DB.dacpac"


REM Target connection string
set TARGET_DB="Data Source=SIFAT\MSSQLSERVER2019;Initial Catalog=ShopSphere-product-service;Integrated Security=True"


REM Publish the database
SQLPackage /Action:Publish /SourceFile:%DACPAC_PATH% /TargetConnectionString:%TARGET_DB% /p:BlockOnPossibleDataLoss=False
