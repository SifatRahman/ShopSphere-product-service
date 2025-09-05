@echo off
REM ---- DBBuild.bat ----
REM Set the path to MSBuild
set MSBUILD_PATH="C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\MSBuild\Current\Bin\MSBuild.exe"


REM Path to your SQL Server project
set PROJECT_PATH=".\DB\DB\DB\DB.sqlproj"
REM Build the project
%MSBUILD_PATH% %PROJECT_PATH% /t:Build /p:Configuration=Release
