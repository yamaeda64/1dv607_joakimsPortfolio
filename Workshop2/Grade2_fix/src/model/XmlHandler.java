package model;

import javax.xml.bind.*;
import java.io.File;
/**
 *  This handles import/export to a XML text file.
 */
public class XmlHandler
{
    private File file = new File("memberRegister.xml"); // the file that will hold all data for the MemberRegister
    
    /**
     *  Exports an XML of the argument memberRegister.
     * @param memberRegister The member register that are to be exported to XML
     * @throws JAXBException Exception in case the XML export doesn't work, i.e. the file is unwritable.
     */
    public void exportXML(MemberRegister memberRegister) throws JAXBException
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(MemberRegister.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(memberRegister, file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Imports a MemberRegister from a XML file. The export function in this class should have been used to produce the
     * XML to assert the XML is compatible.
     * @return a MemberRegister populated with the Members and Boats from the XML file.
     * @throws JAXBException Throws exception in case the import fails.
     */
    public MemberRegister importXML() throws JAXBException
    {
            JAXBContext jaxbContext = JAXBContext.newInstance(MemberRegister.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MemberRegister returnRegister = (MemberRegister) jaxbUnmarshaller.unmarshal(file);
    
            // make ID to be one higher than the highest member ID
            returnRegister.updateMemberID();
    
            return returnRegister;
        
    }
}
