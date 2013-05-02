::Creates new JNLP File for the jar for hosting
@echo off
set StartDir=%CD%
cd %CD%\JNLP_Creator
javac *.java
@echo on
java jnlpgenerator
