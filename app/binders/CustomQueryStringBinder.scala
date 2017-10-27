
package binders

import play.api.mvc.QueryStringBindable

object CustomQueryStringBinder {

  implicit def queryMapBinder(implicit stringBinder: QueryStringBindable[String]) = new QueryStringBindable[Map[String, String]] {
    override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, Map[String, String]]] = {
      val data = for {
        (k, ps) <- params
        if k startsWith key
        p <- ps
      } yield (k.drop(key.length + 1), p)
      if (data.isEmpty) {
        None
      } else {
        Some(Right(data))
      }
    }

    override def unbind(key: String, map: Map[String, String]): String = {
      map.map(x => stringBinder.unbind(s"${key}.${x._1}", x._2)).foldLeft("")((a, b) => a + b + "&")
    }
  }
}
