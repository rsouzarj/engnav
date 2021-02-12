/*    */ package Util;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.TimeZone;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DateDeserializer
/*    */   implements JsonDeserializer<Date>
/*    */ {
/*    */   public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2)
/*    */     throws JsonParseException
/*    */   {
/* 26 */     String date = element.getAsString();
/*    */     
/* 28 */     SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
/* 29 */     format.setTimeZone(TimeZone.getTimeZone("GMT"));
/*    */     try
/*    */     {
/* 32 */       return format.parse(date);
/*    */     } catch (ParseException exp) {}
/* 34 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/diogo.lima/Documents/PEDIDO.jar!/Util/DateDeserializer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */