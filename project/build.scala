import sbt._
import Keys._

object SbtExtras extends Build {
  // This build creates a SBT plugin with handy features *and* bundles the SBT script for distribution.
  val root = Project("sbt-packaging", file(".")) settings(Packaging.settings:_*) settings(
    sbtVersion <<= sbtVersion apply { v =>
      sys.props.getOrElse("sbt.build.version", sys.env.getOrElse("sbt.build.version", v))
    }
  )

  val meta = Project("metapackage", file("metapackage")) settings(MetaPackaging.settings(root):_*)
}
