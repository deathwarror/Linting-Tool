@echo off
Set KeyStore=Storage
Set User=%USERNAME%
keytool -genkey -keystore %KeyStore% -alias %User%
keytool -selfcert -alias %User% -keystore %KeyStore%
keytool -list -keystore %KeyStore%
@echo on
pause
