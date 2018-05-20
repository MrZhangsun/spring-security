# spring-security
inclode zip tool
# generate certification
keytool -genkeypair -alias www.mydomain.com -keyalg RSA -keysize 4096 -keypass changeit -sigalg SHA256withRSA -validity 3650 -keystore www.zhangsun.sit.jks -storetype JKS -storepass changeit
# convert to pkcs12
keytool -importkeystore -srckeystore www.zhangsun.sit.jks -destkeystore www.zhangsun.sit.jks -deststoretype pkcs12