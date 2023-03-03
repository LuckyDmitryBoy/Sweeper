package swepeer;

 class Bomb {//класс, для расстановки боиб, увелечение чисел около бомб, хранения количества бомб и т.д.
private  Matrix bombMap;
private int totalBombs;

Bomb(int totalBombs) {

    this.totalBombs = totalBombs;
    fixBombCount();
}
void start(){
    bombMap=new Matrix(Box.ZERO);
    for (int i=0;i<totalBombs;i++){
    placeBomb();}
}

Box get(Coord coord) {
    return bombMap.get(coord);
}
private void fixBombCount(){
    int maxBomb=Ranges.getSize().x*Ranges.getSize().y/8;
    if(totalBombs>maxBomb){
        totalBombs=maxBomb;
    }
}
     private void placeBomb(){
    while(true){
    Coord coord=Ranges.getRandomCoord();
    if(Box.BOMB==bombMap.get(coord))
        continue;
    bombMap.set(coord,Box.BOMB);
      intNumberAroundBomb(coord);
    break;}
    }
private void intNumberAroundBomb(Coord coord){
    for (Coord around:Ranges.getCoordsAround(coord))
        if (Box.BOMB!=bombMap.get(around))
        bombMap.set(around,bombMap.get(around).getNextNumberBox());
}

     int getTotalBombs() {
    return totalBombs;
     }
 }
