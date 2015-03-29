/**
 * Created by christina on 3/29/15.
 */

import EventStoreFeedBrowser._

object EventStoreChronologicalIterator {
  def printEverythingInOrder(url: String) = {
    var feed = getPageFeed(url)
    var entries = getEntries(feed)
    println(s"processing $url")
    while (entries.length > 0) {
      for (entry <- entries) {
        println(getEntryData(entry))
      }
      println("-----------")
      val previous = getNewerPageUrl(feed)
      println(s"following page: $previous")
      previous match {
        case None => entries = List[String]()
        case _ => {
          feed = getPageFeed(previous.get)
          entries = getEntries(feed)
        }
      }
    }
  }
}
