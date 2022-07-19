Problem: Given a prexisting database of clients and a email/letter template format, create an automated 
processing system that a potential company can use to communicate information to its customers in a fast and
efficient manner.

For this assignment, we are extracting information from a pre-existing database and using this data to generate 
personalized statements using a pre-existing template.

Q. How will your program accommodate new columns (ie. company wants to add a new attribute: [[PO Box]])?
        A. Since we used regular expressions, we won’t have to change anything in our code to accommodate 
        new columns. Our program can support CSV files of any size, as long as it is represented as a 
        column in the first line of the CSV File. Similarly, our generator class can support any templates, 
        as long as the item to be replaced is surrounded by the 2 brackets. 
Q. What happens if a user enters the wrong information?
        A. The CommandProcessor throws an InvalidArgument Exception for any inputs that do not meet the 
        requirements. The CommandProcessor makes sure that every input has a CSV file and an output directory.