javac *.java
pause
java -Djava.security.manager ClassLoaderTest
pause
java -Djava.security.manager -Djava.security.policy=policyfile.policy ClassLoaderTest
pause