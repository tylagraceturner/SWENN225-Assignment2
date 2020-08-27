class QueueItem{
    int row;
    int col;
    int dist;
    public QueueItem(int x, int y, int w){
        this.row=x;
        this.col=y;
        this.dist=w;
    }
    public QueueItem(int x, int y){
        this.row=x;
        this.col=y;
    }
}
