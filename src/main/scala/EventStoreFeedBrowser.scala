import scala.xml.{XML, Node}
import scalaj.http.Http

/**
 * Created by christina on 3/29/15.
 */
object EventStoreFeedBrowser {
  def getPageFeed(url: String): Node = {
    val xmlString = Http(url).header("Accept", "application/atom+xml").asString.body
    XML.loadString(xmlString)
  }

  def getEntries(xml: Node): List[String] = {
    (xml \\ "entry" \\ "link"  filter attributeValueEquals("rel", "edit") map mapToAttributeValue("href")).toList.reverse
  }

  def getNewerPageUrl(xml: Node): Option[String] = {
    getHrefForLink("previous", xml)
  }

  def getOldestPageUrl(xml: Node): Option[String] = {
    getHrefForLink("last", xml)
  }

  def getEntryData(url: String): String = {
    val xmlString = Http(url).header("Accept", "application/atom+xml").asString.body
    val xml = XML.loadString(xmlString)
    println((xml \\ "entry" \\ "content" \\ "eventNumber").text)
    (xml \\ "entry" \\ "content" \\ "data").toString()
  }

  private def attributeValueEquals(attribute: String, value: String)(node: Node) = {
    (node \ ("@" + attribute)).text == value
  }

  private def mapToAttributeValue(attribute: String)(node: Node) : String = {
    (node \ ("@" + attribute)).text
  }

  private def getHrefForLink(relType: String, xml: Node): Option[String] = {
    val link = xml \\ "link" filter attributeValueEquals("rel", relType) map mapToAttributeValue("href")
    link.length match {
      case 0 => None: Option[String]
      case _ => Some(link(0))
    }
  }

}
