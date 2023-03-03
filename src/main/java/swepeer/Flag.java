package swepeer;

class Flag {
    private Matrix flagMap;
    private int getCountOfClosedBoxes;
    void start(){
        flagMap=new Matrix(Box.CLOSED);
        getCountOfClosedBoxes=Ranges.getSize().x*Ranges.getSize().y;
    }
    Box get(Coord coord){
return flagMap.get(coord);
    }
    void setOpenedToBox(Coord coord){
        flagMap.set(coord,Box.OPENED);
        getCountOfClosedBoxes--;
    }
    void toggleFlagedToBox(Coord coord){
        switch (flagMap.get(coord)){
            case FLAGED :setClosedToBox(coord);break;
            case CLOSED:setFlagedToBox(coord);break;
        }
    }
    private void setClosedToBox(Coord coord){
        flagMap.set(coord,Box.CLOSED);
    }
    void setFlagedToBox(Coord coord){
        flagMap.set(coord,Box.FLAGED);
    }

     int getCountOfClosedBoxes() {
        return getCountOfClosedBoxes;
    }

   void setBombedToBox(Coord coord) {
        flagMap.set(coord,Box.BOMBED);
    }

  void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord)==Box.CLOSED)
            flagMap.set(coord,Box.OPENED);
    }
    void setNoBombsToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord)==Box.FLAGED)
            flagMap.set(coord,Box.NOBOMB);
    }
   int getCountOfFlagedBoxesAround(Coord coord){
        int count=0;
        for(Coord around:Ranges.getCoordsAround(coord))
            if (flagMap.get(around)==Box.FLAGED)
                count++;
        return count;
   }
}
