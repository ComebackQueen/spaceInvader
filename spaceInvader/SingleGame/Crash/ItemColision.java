
package Crash;

import Item.Item;
import Player.Player;

public class ItemColision {
	public boolean itemDrop = true;
	public int item = 0;
	public void item_colision(Item it, Player py) {

		if ((py.x >= it.x && py.x <= it.x + 50) && (py.y >= it.y && py.y <= it.y + 64) // 왼쪽위모서리 충돌 감지
				|| (py.x >= it.x && py.x <= it.x + 50) && (py.y + 35 >= it.y && py.y + 35 <= it.y + 64) // 왼쪽아래모서리 충돌 감지
				|| (py.x + 45 >= it.x && py.x + 45 <= it.x + 50) && (py.y >= it.y && py.y <= it.y + 64) // 오른쪽위모서리 충돌 감지
				|| (py.x + 45 >= it.x && py.x + 45 <= it.x + 50) && (py.y + 35 >= it.y && py.y + 35 <= it.y + 64)) // 오른쪽아래모서리
																												// 충돌 감지
		{

			if (itemDrop) {
				item++;
				it.itemNum = item;
				py.getItem = it.itemNum;
				itemDrop = false;
				it.itemOn = false;
				it.save = false;
				it.random = 0;
				it.iy = it.p.y = it.y = 0;
				it.p.x = it.x = it.ix = 1280;
				// 있어야한다.
				
				if(item == 3)
				{
					item = 0;
				}
			}

		}
	}
}