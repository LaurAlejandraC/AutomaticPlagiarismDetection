

public class Test {
	static class Dog{
		private String name;
		
		
		public Dog(String name) {
			super();
			this.name = name;
		}


		@Override
		public String toString() {
			return name;
		}
	}
	public static void main(String[] args) {
		Dog dog = new Dog("toby");
		Dog dogB = new Dog("Felipe");
		
		dogB.name = "fdjklas";
	
		System.out.println(dog);
		System.out.println(dogB);
	}
}
