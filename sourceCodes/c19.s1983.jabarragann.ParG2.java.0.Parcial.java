package parcial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class Parcial 
{

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan;
		File f = new File( "input.txt" );
		if ( f.exists() ) {
			scan = new Scanner( f );
			//System.setOut( new PrintStream( "output.txt" ) );
		} else {
			scan = new Scanner( System.in );
		}

		int t = Integer.parseInt( scan.nextLine() );
		for ( int test = 0; test < t; test++ ) {
			int n = Integer.parseInt( scan.nextLine() );//numero de comandos
			System.out.printf("Case #%d:\n",(test+1));

			
			MyArrayList<Integer>container= new MyArrayList<Integer>();
			boolean isStack=true;
			boolean isQueue=true;
			boolean isImposible=false;
			boolean notSure=true;
			/////////////////////////////////////
			for( int i = 0 ; i < n ; i++ )
			{
				int arr[] = readInts(scan.nextLine());
				int command = arr[0]; // tipo de comando (1 o 2)
				int x = arr[1]; // dato que se va a agregar o sacar

				switch (command)
				{
				case 1:
					container.add(x);
					break;
				case 2:
					if(container.size()!=1)
					{
						if(container.get(container.size()-1)==x)
						{
							container.remove(container.size()-1);
							if(isQueue!=isStack)
								notSure=false;
							isQueue=false;
							
						}
						else if (container.get(0)==x)
						{
							container.remove(0);
							if(isQueue!=isStack)
								notSure=false;
							isStack=false;
							
						}
						else 
						{
							notSure=false;
							isImposible=true;
						}
					}
					else
					{
						if (container.get(0)==x)
						{
							container.remove(0);
						}
						else 
						{
							isImposible=true;
							notSure=false;
						}
					}
					
					break;
				}
			}
			if(notSure) System.out.println("no estoy seguro");
			else if(isImposible) System.out.println("imposible");
			else if(isStack && !isQueue) System.out.println("stack");
			else if(!isStack && isQueue)System.out.println("queue");
			else if(isStack==isQueue) System.out.println("imposible");
			else System.out.println("no estoy seguro");
		}
	}
	public static int[] readInts(String cad){
		String line[] = cad.split( " " );
		int arr[] = new int[line.length];
		for( int i = 0 ; i < arr.length ; i++ ){
			arr[i] = Integer.parseInt( line[i] );
		}
		return arr;
	}


	public static class MyArrayList<AnyType> implements Iterable<AnyType>
	{
		/**
		 * Construct an empty ArrayList.
		 */
		public MyArrayList( )
		{
			doClear( );
		}

		/**
		 * Returns the number of items in this collection.
		 * @return the number of items in this collection.
		 */
		public int size( )
		{
			return theSize;
		}

		/**
		 * Returns true if this collection is empty.
		 * @return true if this collection is empty.
		 */ 
		public boolean isEmpty( )
		{
			return size( ) == 0;
		}

		/**
		 * Returns the item at position idx.
		 * @param idx the index to search in.
		 * @throws ArrayIndexOutOfBoundsException if index is out of range.
		 */
		public AnyType get( int idx )
		{
			if( idx < 0 || idx >= size( ) )
				throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
			return theItems[ idx ];    
		}

		/**
		 * Changes the item at position idx.
		 * @param idx the index to change.
		 * @param newVal the new value.
		 * @return the old value.
		 * @throws ArrayIndexOutOfBoundsException if index is out of range.
		 */
		public AnyType set( int idx, AnyType newVal )
		{
			if( idx < 0 || idx >= size( ) )
				throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
			AnyType old = theItems[ idx ];    
			theItems[ idx ] = newVal;

			return old;    
		}

		@SuppressWarnings("unchecked")
		public void ensureCapacity( int newCapacity )
		{
			if( newCapacity < theSize )
				return;

			AnyType [ ] old = theItems;
			theItems = (AnyType []) new Object[ newCapacity ];
			for( int i = 0; i < size( ); i++ )
				theItems[ i ] = old[ i ];
		}

		/**
		 * Adds an item to this collection, at the end.
		 * @param x any object.
		 * @return true.
		 */
		public boolean add( AnyType x )
		{
			add( size( ), x );
			return true;            
		}

		/**
		 * Adds an item to this collection, at the specified index.
		 * @param x any object.
		 * @return true.
		 */
		public void add( int idx, AnyType x )
		{
			if( theItems.length == size( ) )
				ensureCapacity( size( ) * 2 + 1 );

			for( int i = theSize; i > idx; i-- )
				theItems[ i ] = theItems[ i - 1 ];

			theItems[ idx ] = x;
			theSize++;  
		}

		/**
		 * Removes an item from this collection.
		 * @param idx the index of the object.
		 * @return the item was removed from the collection.
		 */
		public AnyType remove( int idx )
		{
			AnyType removedItem = theItems[ idx ];

			for( int i = idx; i < size( ) - 1; i++ )
				theItems[ i ] = theItems[ i + 1 ];
			theSize--;    

			return removedItem;
		}

		/**
		 * Change the size of this collection to zero.
		 */
		public void clear( )
		{
			doClear( );
		}

		private void doClear( )
		{
			theSize = 0;
			ensureCapacity( DEFAULT_CAPACITY );
		}

		/**
		 * Obtains an Iterator object used to traverse the collection.
		 * @return an iterator positioned prior to the first element.
		 */
		public java.util.Iterator<AnyType> iterator( )
		{
			return new ArrayListIterator( );
		}

		/**
		 * Returns a String representation of this collection.
		 */
		public String toString( )
		{
			StringBuilder sb = new StringBuilder( "[ " );

			for( AnyType x : this )
				sb.append( x + " " );
			sb.append( "]" );

			return new String( sb );
		}

		/**
		 * This is the implementation of the ArrayListIterator.
		 * It maintains a notion of a current position and of
		 * course the implicit reference to the MyArrayList.
		 */
		private class ArrayListIterator implements java.util.Iterator<AnyType>
		{
			private int current = 0;
			private boolean okToRemove = false;

			public boolean hasNext( )
			{
				return current < size( );
			}


			public AnyType next( )
			{
				if( !hasNext( ) ) 
					throw new java.util.NoSuchElementException( ); 

				okToRemove = true;    
				return theItems[ current++ ];
			}

			public void remove( )
			{
				if( !okToRemove )
					throw new IllegalStateException( );

				MyArrayList.this.remove( --current );
				okToRemove = false;
			}
		}

		private static final int DEFAULT_CAPACITY = 10;

		private AnyType [ ] theItems;
		private int theSize;
	}


}
