package meta.compile.GeneratedPackage

sealed trait GeneratedPackage

case class vanillaPackage(pkgName: String) extends GeneratedPackage {
    override def toString = {
        "generated." + pkgName 
    }
}

case class mergedPackage(pkgName: String) extends GeneratedPackage {
    override def toString = {
        "generated." + pkgName + "_merged"
    }
}
case class ssoPackage(pkgName: String) extends GeneratedPackage {
    override def toString = {
        "generated." + pkgName + "_sso"
    }
}