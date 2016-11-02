public class Tester {

	public static void main(String[] args) {
		int[] hall = {1, 1, 2, 2};
		Robot robot = new Robot(hall, 1, true);
		System.out.println("moves: " + robot.clearHall());
	}
}

/************************************************************
 * Head First Java / Chapter 4 (Selected reading questions) *
 ************************************************************/

// 5.
/*

In main, numPeople is: 10
In someMethod, numPeople is: 20
In main, numPeople is: 10

*/

// 8.
/*

It could return an array of names.
ex:
public static String[] returnListOfNames() {
	String[] names = {"John Diggle", "Malcolm Reynolds", "Gaius Baltar"};
	return names;
}

*/

// 9.
/*

setters set a private instance variable to the given parameter
ex: 
private int x = 0;
public void setX(int x) {
	this.x = x;
}

getters return a private instance variable
ex:
private int x = 2;
public int getX() {
	return x;
}

Getters and setters begin to make more sense when you start creating more
complex programs. It's not fun at all to debug a 600+ line program which
has zero encapsulation. Everything is interacting in a gigantic web of
spaghetti which implodes when you decide to look at it too hard.

You might not want your instance variables to be some values. For
example, one might not want int x to ever be 0. If you don't encapsulate,
anyone can just set x to zero. There are no safeguards in place.

With encapsulation (getters and setters), each object handles it's own instance
variables. Debugging this is relatively easy. You know for certain that only
that object is capable of screwing itself over. There is only one class file
you have to look over to see what the problem is.

*/

// 10.
/*

"Why would you want to make your instance variables private so that they cannot
be seen, altered, or changed in any way by the outside world"
	So you avoid spaghetti code which is agonizing to debug. (above)

"What mechanism does Java use to allow one class to access private instance
variables of another class?"
	Methods. Specifically, the getter and setter variety. Getters and setters
	aren't really their own special thing like they are in some other
	languages.

*/

// 11.
/*

Let's say I'm making an object that prints out a message

...
public text = "";
public myPrinter() {}
public printMessage() {
	System.out.println(text);
}
...

My friend Jayne would print out text like this:

...
myPrinter myObject = new myPrinter();
myObject.text = "Hello!";
myObject.printMessage();
...
// output: Hello!

I decided that I want to change the 'text' variable name to 'message', so that
it matches the name of the method 'printMessage'.

// changes: 
...
 -  public text = "";
 +  public message = "";
	public myPrinter() {}
	public printMessage() {
	-	System.out.println(text);
	+	System.out.println(message);
	}
...

// final code:
...
public message = "";
public myPrinter() {}
public printMessage() {
	System.out.println(message);
}
...

Now Jayne is screwed over, because of the changes I made!

...
myPrinter myObject = new myPrinter();
myObject.text = "Hello!";
myObject.printMessage();
...
// Doesn't compile because myObject doesn't have an instance variable named
// 'text'.

If I had used getters and setters, I could've changed my instance variables
without changing the names of the getter and setter methods. Jayne shouldn't 
have to care about the internal workings of my object - Why even use OOP if 
this is the case?

*/

// 15.
/*

"Got an s3 match!" will print twice, nothing else will print.

*/