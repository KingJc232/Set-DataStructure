#MakeFile Script for SET ADT H.W 


main: *.java
	@javac *.java 

edit:
	@vim Main.java

clean:
	@rm *.class

run:
	@java Main
