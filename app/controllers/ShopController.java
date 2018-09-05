package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import domain.Item;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import domain.Shop;
import web.InputItem;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShopController extends Controller {

    private final Shop shop;

    @Inject
    public ShopController(Shop shop){
        this.shop = shop;
    }


    public Result list() {
        return ok(Json.toJson(shop.list()));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {

        InputItem item = getInputItem();

        return ok("Item Created" + Json.toJson(shop.create(item.getName(), item.getPrice())));
    }

    private InputItem getInputItem() {
        JsonNode json = request().body().asJson();
        InputItem item;

        item = Json.fromJson(json, InputItem.class);
        return item;
    }

    public Result details(Long id) {

        return ok(Json.toJson(shop.get(id)));
    }
    public Result update(Long id) {

        InputItem item = getInputItem();
        return ok("Item Created" + Json.toJson(shop.update(id, item.getName(), item.getPrice())));

    }
    public Result delete(Long id) {

        if(shop.delete(id)){
            return ok("Item deleted :: " + id );
        } else {
            return notFound("Id not found :: "+ id);
        }

    }
}
