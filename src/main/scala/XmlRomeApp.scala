import scala.xml._
import scala.xml.XML
import scalaj.http.Http


/**
 * Created by christina on 3/29/15.
 */
object XmlRomeApp extends App {

//  EventStoreChronologicalIterator.printEverythingInOrder("")
//
//  println("++++++++++++++++++")
//
//  EventStoreChronologicalIterator.printEverythingInOrder("http://localhost:2113/streams/newstream/73/forward/20")
//
//  println("++++++++++++++++++")

  EventStoreChronologicalIterator.printEverythingInOrder("http://localhost:2113/streams/newstream/0/forward/20")


//  val theUrl = "http://localhost:2113/streams/newstream/0/forward/20"
//  val xmlString = Http(theUrl).header("Accept", "application/atom+xml").asString.body
//  val xml = XML.loadString(xmlString)
//
//
//  val last = xml \\ "link" filter attributeValueEquals("rel", "last") map mapToAttributeValue("href")
//  val previous = xml \\ "link" filter attributeValueEquals("rel", "previous") map mapToAttributeValue("href")
//
//  println(s"last: $last")
//  println(s"previous: $previous")
//
//  val entries = xml \\ "entry" \\ "link"  filter attributeValueEquals("rel", "edit") map mapToAttributeValue("href")
//  println(entries.count(_ => true))
//  println(entries)
//
//  def attributeValueEquals(value: String)(node: Node) = {
//    node.attributes.exists(_.value.text == value)
//  }
//
//  def attributeValueEquals(attribute: String, value: String)(node: Node) = {
//    //node.attributes.filter(a => a.).exists(_.value.text == value)
//    (node \ ("@" + attribute)).text == value
//  }
//
//  def mapToAttributeValue(attribute: String)(node: Node) : String = {
//    (node \ ("@" + attribute)).text
//  }
//
//  def getByAtt(e: Elem, att: String, value: String) = {
//    def filterAttribute(node: Node, att: String, value: String) =  (node \ ("@" + att)).text == value
//    e \\ "_" filter { n=> filterAttribute(n, att, value)}
//  }
}


//object EventStoreFeedBrowser {
//  // if it has no previous link then it's the most recent page
//  def isMostRecentPage(url: String): Boolean = {
//
//
//
//  }
//  // if entries
//  def hasMessages(url: String): Boolean = {
//
//  }
//}