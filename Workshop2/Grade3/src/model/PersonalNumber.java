package model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class PersonalNumber
{
    String personalNumber;
    
    
    public void setPersonalNumber(String personalNumber)
    {
        try
        {
            
            StringBuilder bf = new StringBuilder();
            bf.append(personalNumber.substring(0,4));
            bf.append("-");
            bf.append(personalNumber.substring(4,6));
            bf.append("-");
            bf.append(personalNumber.substring(6,8));
            LocalDate date = LocalDate.parse(bf);
            
        }
        catch(DateTimeException e)
        {
            throw new InputMismatchException("You entered a faulty date or wrong format");
        }
        
        if(personalNumber.charAt(8) != '-')
        {
            throw new InputMismatchException("You didn't use a '-' between birthdate and personal digits");
        }
        
        if(!isCorrectControlDigit(personalNumber))
        {
            throw new InputMismatchException("Your security digits doesn't apply to a valid swedish social security number");
        }
        this.personalNumber = personalNumber;
    }
    public String getPersonalNumber()
    {
        return personalNumber;
    }
    
    public int getFirstPart(String personalNumberRef)
    {
        return Integer.parseInt(personalNumberRef.substring(0, 6));
    }
    
    public int getSecondPart(String personalNumberRef)
    {
        return Integer.parseInt(personalNumberRef.substring(7,11));
    }
    
    
    public boolean isFemaleNumber()
    {
        int thirdDigit = Integer.parseInt(personalNumber.substring(11,12));
        if (thirdDigit % 2 == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isMaleNumber()
    {
        int thirdDigit = Integer.parseInt(personalNumber.substring(11,12));
        if (thirdDigit % 2 == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean areEqual(String personalNumberRef1, String personalNumberRef2)
    {
        if (personalNumberRef1 == personalNumberRef2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isCorrectControlDigit(String personalNumberRef)
    {
        int[] personalArray = new int[10];
        int counter = 0;
        for(int i = 2; i < 12; i++)
        {
            if(i != 8)
            {
                
                personalArray[counter] = Integer.parseInt(personalNumberRef.substring(i, i+1));
                counter++;
            }
        }
        
        int personalNumberSum = 0;
        int controlDigit;
        
        for(int i = 0; i < 10; i++)
        {
            
            int numAtI = personalArray[i];
            
            if(i % 2 == 0)
            {
                if(numAtI >= 5)
                {
                    personalNumberSum++;
                    personalNumberSum += (numAtI * 2) % 10;
                }
                else
                {
                    personalNumberSum += (numAtI * 2);
                }
            }
            else
            {
                personalNumberSum += numAtI;
            }
            
        }
        controlDigit = 10 - (personalNumberSum % 10);
        if (controlDigit == Integer.parseInt(personalNumberRef.substring(12)))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public LocalDate getBirthdate()
    {
        StringBuilder bf = new StringBuilder();
        bf.append(personalNumber.substring(0,4));
        bf.append("-");
        bf.append(personalNumber.substring(4,6));
        bf.append("-");
        bf.append(personalNumber.substring(6,8));
        return LocalDate.parse(bf);
    }
    
}
