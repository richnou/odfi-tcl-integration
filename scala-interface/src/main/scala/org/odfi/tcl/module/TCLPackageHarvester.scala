package org.odfi.tcl.module

import java.io.File

import org.odfi.indesign.core.harvest.Harvester
import org.odfi.indesign.core.harvest.fs.HarvestedFile

class TCLPackageHarvester extends Harvester {
  
  this.onDeliverFor[HarvestedFile] {
    case folder if (folder.isDirectory && new File(folder.path.toFile(),"pkgIndex.tcl").exists) => 
     // println("Tcl package delivered "+folder+" from "+this.parentHarvester)
      gather(new TCLPackageFolder(folder))
      true
  }
  
}

class TCLPackageFolder(base:HarvestedFile) extends HarvestedFile(base.path) {
  this.deriveFrom(base)
  
  def getIndexFile = new File(base.path.toFile,"pkgIndex.tcl")
}