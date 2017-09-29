package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by joakimbergqvist on 2017-09-15.
 */
public class XmlHandler
{
    private File file = new File("memberRegister.xml");
    // Constructor
    public XmlHandler()
    {
        
    }
    
    public void exportXML(MemberRegister inList) throws JAXBException
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(MemberRegister.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(inList, file);
        }
        catch(Exception e) // TODO, specify exception
        {
            e.printStackTrace(); // TODO, change to more user firendly exception
        }
        
    }
    
    public MemberRegister importXML() throws JAXBException
    {
        
        JAXBContext jaxbContext = JAXBContext.newInstance(MemberRegister.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        MemberRegister returnRegister = (MemberRegister) jaxbUnmarshaller.unmarshal(file);
        // make ID to be one higher than the highest member ID
        int memberID = 0;
        for(int i = 0; i < returnRegister.getMembers().size(); i++)
        {
            if(returnRegister.getMembers().get(i).getMemberID() >= memberID)
            {
                memberID = returnRegister.getMembers().get(i).getMemberID() + 1;
            }
        }
        returnRegister.setMemberIdCounter(memberID);
        return returnRegister;
        
    }
}
