/**
 * Represents the items is queue in Cluedo.
 */
class QueueItem{
    //Variable Names
    int row;
    int col;
    int dist;

    //Constructor A
    public QueueItem(int x, int y, int w){
        this.row=x;
        this.col=y;
        this.dist=w;
    }

    //Constructor B
    public QueueItem(int x, int y){
        this.row=x;
        this.col=y;
    }
}
