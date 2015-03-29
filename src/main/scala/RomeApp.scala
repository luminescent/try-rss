import com.sun.syndication.feed.synd._
import com.sun.syndication.io.impl.Atom10Parser
import com.sun.syndication.io.{SyndFeedInput, XmlReader}
import java.net.{HttpURLConnection, URL}
import java.util.{List => JList}
import scala.collection.JavaConverters._

object AtomFeedUrlConnectionFactory {
  def getUrlConnection(url: String): HttpURLConnection = {
    val myUrl = new URL(url)
    val myURLConnection = myUrl.openConnection().asInstanceOf[HttpURLConnection] ;
    myURLConnection.setRequestProperty("Accept", "application/atom+xml")

    myURLConnection
  }
}

object RomeApp extends App {
//  val url = new URL("http://localhost:2113/streams/newstream/30")
//  val myURLConnection = url.openConnection().asInstanceOf[HttpURLConnection] ;
//  myURLConnection.setRequestProperty("Accept", "application/atom+xml")

  val se = new SyndEntryImpl()
  se.setUri("http://localhost:2113/streams/newstream/30")

  var xr = new XmlReader(AtomFeedUrlConnectionFactory.getUrlConnection("http://localhost:2113/streams/newstream/30"))

  val entry = Atom10Parser.parseEntry(xr, "");


  xr = new XmlReader(AtomFeedUrlConnectionFactory.getUrlConnection("http://localhost:2113/streams/newstream"))

  val feed: SyndFeed = new SyndFeedInput().build(xr) //new XmlReader(new URL("http://localhost:2113/streams/newstream")))
  val rss_title = feed.getTitle
  val rss_ex = feed.getTitleEx.getValue
  val rss_desc = feed.getDescription

  val rss_links = feed.getLinks.asInstanceOf[JList[SyndLinkImpl]].asScala
  for (link <- rss_links) {
    println(link)
  }



  val rss_entries = feed.getEntries.asInstanceOf[JList[SyndEntry]].asScala
  for (entry <- rss_entries) {
    println("------------------------------")
    println(entry.getLink)
  }





//  for (entry <- rss_entries;
//       content <- entry.getContents.asInstanceOf[JList[SyndContent]].asScala) {
//    println("------------------------------")
//    println(content.getValue)
//  }
}