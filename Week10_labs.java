import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map;

/** The goal of this practice is not to "get the answer" through AI or some other means, but for you to think through the questions and
* come up with a strategy. You can decide not to do it at your own cost.
*/

/**
* In the following, write code to achieve what's asked. You don't need to but if you want to very the accuracy of your code,
* include statements to print the result.
*/

/** Submit to the TA, and he will assign you a grade based on a few selected responses.  */





public class Week10_labs
{
    public static void main(String[] args)
	{
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

		// Collect elements into a Set
		Set<String> fruitSet = fruit.stream().collect(Collectors.toSet());
        // Collect the fruit into groups based on their first character
		Map<Character, List<String>> fruitByFirstChar = fruit.stream().collect(Collectors.groupingBy(s -> s.charAt(0)));
		// Group fruit by the length of the name
		Map<Integer, List<String>> fruitByLength = fruit.stream().collect(Collectors.groupingBy(String::length));
		//Collect the fruit that has erry in it
		List<String> fruitWithErry = fruit.stream().filter(s -> s.contains("erry")).collect(Collectors.toList());
		//Create a partition of fruit based on if it contains erry
		Map<Boolean, List<String>> fruitPartitioned = fruit.stream().collect(Collectors.partitioningBy(s -> s.contains("erry")));

		//collect/ the fruit that has 5 or less symbols
		List<String> fruitWith5OrLessSymbols = fruit.stream().filter(s -> s.length() <= 5).collect(Collectors.toList());

		//find the total number of symbols in all the fruit stored
		int totalSymbols = fruit.stream().mapToInt(String::length).sum();


		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        // Partition data based on if >=50
		Map<Boolean, List<Integer>> dataPartitioned = data.stream().collect(Collectors.partitioningBy(x -> x >= 50));

		//divide data into groups based on the remainder when divided by 7
		Map<Integer, List<Integer>> dataByRemainder = data.stream().collect(Collectors.groupingBy(x -> x % 7));

		//find the sum of the data
		int sumData = data.stream().mapToInt(Integer::intValue).sum();

		//collect the unique values
		Set<Integer> uniqueValues = data.stream().collect(Collectors.toSet());

        //compute the cube of each values
		List<Integer> cubes = data.stream().map(x -> x * x * x).collect(Collectors.toList());
		//find the sum of the cubes of each value
		int sumCubes = cubes.stream().mapToInt(Integer::intValue).sum();

		//increase the value of each element by 5
		List<Integer> increasedValues = data.stream().map(x -> x + 5).collect(Collectors.toList());

		//compute the cube of the even values
		List<Integer> evenCubes = data.stream().filter(x -> x % 2 == 0).map(x -> x * x * x).collect(Collectors.toList());


		System.out.println("=== FRUIT RESULTS ===");
		System.out.println("\nSet: " + fruitSet);
		System.out.println("\nGrouped by first char: " + fruitByFirstChar);
		System.out.println("\nGrouped by length: " + fruitByLength);
		System.out.println("\nContains 'erry': " + fruitWithErry);
		System.out.println("\nPartitioned by 'erry': " + fruitPartitioned);
		System.out.println("\nLength <= 5: " + fruitWith5OrLessSymbols);
		System.out.println("Total symbols: " + totalSymbols);

		System.out.println("\n=== DATA RESULTS ===");
		System.out.println("\nPartition >= 50: " + dataPartitioned);
		System.out.println("\nGrouped by remainder (mod 7): " + dataByRemainder);
		System.out.println("\nSum of data: " + sumData);
		System.out.println("\nUnique values: " + uniqueValues);
		System.out.println("\nCubes: " + cubes);
		System.out.println("\nSum of cubes: " + sumCubes);
		System.out.println("\nValues + 5: " + increasedValues);
		System.out.println("\nEven cubes: " + evenCubes);
	}
}
