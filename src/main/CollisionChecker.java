package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.tileMapNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.tileMapNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.tileMapNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.tileMapNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.tileMapNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.tileMapNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.tileMapNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.tileMapNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision)
                    entity.collisionOn = true;
                break;

        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int indexOfOBJ =0; indexOfOBJ < gp.obj.length; indexOfOBJ ++ ) {
            if(gp.obj[indexOfOBJ]!=null) {
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get obj solid area position
                gp.obj[indexOfOBJ].solidArea.x = gp.obj[indexOfOBJ].worldX + gp.obj[indexOfOBJ].solidArea.x;
                gp.obj[indexOfOBJ].solidArea.y = gp.obj[indexOfOBJ].worldY + gp.obj[indexOfOBJ].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                            if(gp.obj[indexOfOBJ].collision) {
                                entity.collisionOn =true;
                            }
                            if(player) {
                                index=indexOfOBJ;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                            if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                                if(gp.obj[indexOfOBJ].collision) {
                                    entity.collisionOn =true;
                                }
                                if(player) {
                                    index=indexOfOBJ;
                                }
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                            if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                                if(gp.obj[indexOfOBJ].collision) {
                                    entity.collisionOn =true;
                                }
                                if(player) {
                                    index=indexOfOBJ;
                                }
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                            if(entity.solidArea.intersects(gp.obj[indexOfOBJ].solidArea)) {
                                if(gp.obj[indexOfOBJ].collision) {
                                    entity.collisionOn =true;
                                }
                                if(player) {
                                    index=indexOfOBJ;
                                }
                            }
                        }
                        break;

                }
                entity.solidArea.x =entity.solidAreaDefaultX;
                entity.solidArea.y =entity.solidAreaDefaultY;

                gp.obj[indexOfOBJ].solidArea.x =gp.obj[indexOfOBJ].solidAreaDefaultX;
                gp.obj[indexOfOBJ].solidArea.y =gp.obj[indexOfOBJ].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for(int indexOfTg =0; indexOfTg < target.length; indexOfTg ++ ) {
            if(target[indexOfTg]!=null && target[indexOfTg]!=entity) {
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get obj solid area position
                target[indexOfTg].solidArea.x = target[indexOfTg].worldX + target[indexOfTg].solidArea.x;
                target[indexOfTg].solidArea.y = target[indexOfTg].worldY + target[indexOfTg].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                                entity.collisionOn =true;
                                index=indexOfTg;

                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                            if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                                    entity.collisionOn =true;
                                    index=indexOfTg;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                            if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                                    entity.collisionOn =true;
                                    index=indexOfTg;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                            if(entity.solidArea.intersects(target[indexOfTg].solidArea)) {
                                    entity.collisionOn =true;
                                    index=indexOfTg;
                            }
                        }
                        break;

                }
                entity.solidArea.x =entity.solidAreaDefaultX;
                entity.solidArea.y =entity.solidAreaDefaultY;

                target[indexOfTg].solidArea.x =target[indexOfTg].solidAreaDefaultX;
                target[indexOfTg].solidArea.y =target[indexOfTg].solidAreaDefaultY;
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity){

                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get player solid area position
                gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y-=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn =true;

                        }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            if(entity.solidArea.intersects(gp.player.solidArea)) {
                                entity.collisionOn =true;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x+=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            if(entity.solidArea.intersects(gp.player.solidArea)) {
                                entity.collisionOn =true;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.player.solidArea)) {
                            if(entity.solidArea.intersects(gp.player.solidArea)) {
                                entity.collisionOn =true;

                            }
                        }
                        break;

                }
                entity.solidArea.x =entity.solidAreaDefaultX;
                entity.solidArea.y =entity.solidAreaDefaultY;

                gp.player.solidArea.x =gp.player.solidAreaDefaultX;
                gp.player.solidArea.y =gp.player.solidAreaDefaultY;
    }

}