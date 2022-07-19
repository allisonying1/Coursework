Problem: Given a prexisting database of clients and a email/letter template format, create an automated 
processing system that a potential company can use to communicate information to its customers in a fast and
efficient manner.

-----------------------------------------------------------------------------------------------------------
Q. How will your program accommodate new columns (ie. company wants to add a new attribute: [[PO Box]])?
        
        A. Since our program uses regular expressions, we won’t have to change anything in the code to
        accommodate new columns. This program can support CSV files of any size. Similarly, the generator 
        class can support any templates, as long as the item to be replaced is surrounded by the 2 brackets. 

-----------------------------------------------------------------------------------------------------------
Q. What happens if a user enters the wrong information?
        
        A. The CommandProcessor throws an InvalidArgument Exception for any inputs that do not meet the 
        requirements. The CommandProcessor makes sure that every input has a CSV file and an output directory.
