import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
     public static final String FILE_NAME = "USPizzaDataset.csv";
    public static void main(String[] args)
    {

        try
        {
            Scanner file = new Scanner(new File(FILE_NAME));
            int count = 0, countOside = 0;
            double min = Double.MAX_VALUE, minOside = Double.MAX_VALUE, max = Double.MIN_VALUE;
            double maxOside = Double.MIN_VALUE, price;
            double average, averageOside, sum= 0.0, sumOside = 0.0;
            String line, minName = "", maxName = "", minNameOside = "", MaxNameOside = "",city, name;
            String[] parts;
            //skip the headings
            file.nextLine();//Pulls off 1 line from the file


            //LOOP THROUGH THE FILE
            while(file.hasNextLine())
            {
                line = file.nextLine();

                parts = line.split(",");




                //Price comes from index 6
                //Convert from string into a double
                price = (parts[6]==null)? 0.0 : Double.parseDouble(parts[6]);
                city = parts[2];
                name = parts[9];

                //national count goes up
                count++;
                sum += price;
                //Make decision about national max and min
                if(price>max)
                {
                    max = price;
                    maxName = name;
                }

                if(price<min)
                {
                    min = price;
                    minName = name;
                }
                //Oceanside statistics
                if("Oceanside".equalsIgnoreCase(city))
                {
                    countOside++;
                    sumOside += price;
                    if(price>maxOside)
                    {
                        maxOside = price;
                        MaxNameOside = name;
                    }

                    if(price<minOside)
                    {
                        minOside = price;
                        minNameOside = name;
                    }
                }

            }
            file.close();
            System.out.println("US Pizza Dataset: National ");
            System.out.println("Number of entries reported: "+count);
            System.out.println("Highest priced pizzeria (nationally): "+maxName);
            System.out.println("Pizza Price $"+ max);
            System.out.println("Highest priced pizzeria (nationally): "+minName);
            System.out.println("Pizza Price $"+ min);

            System.out.println("Average price of pizza (nationally): "+sum/count);

            System.out.println("\n\nUS Pizza Dataset: Oceanside ");
            System.out.println("Number of entries reported: "+countOside);
            System.out.println("Highest priced pizzeria (nationally): "+MaxNameOside);
            System.out.println("Pizza Price $"+ maxOside);
            System.out.println("Highest priced pizzeria (nationally): "+minNameOside);
            System.out.println("Pizza Price $"+ minOside);

            System.out.println("Average price of pizza (nationally): "+sumOside/countOside);

        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error Opening: "+ e.getMessage());
        }

    }
}
