/*This activity modified from
 * open source example at
 * mysamplecode.com
 */

package com.example.watsonyourplate;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class ListViewCheckboxesActivity extends Activity {
 
 MyCustomAdapter dataAdapter = null;
 
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.diet_info);
 
  //Generate list View from ArrayList
  displayListView();
 
  //checkButtonClick();
 
 }
 
 private void displayListView() {
 
  //Array list of diets
  ArrayList<Diet> dietList = new ArrayList<Diet>();
  Diet diet = new Diet("Vegan","No animal-derived ingredients.");
  dietList.add(diet);
  diet = new Diet("Vegetarian","No animal body parts.");
  dietList.add(diet);
  diet = new Diet("Dairy-free","No dairy ingredients.");
  dietList.add(diet);
  diet = new Diet("Nut-free","No nuts of any kind.");
  dietList.add(diet);
  diet = new Diet("Gluten-free","No gluten ingredients.");
  dietList.add(diet);
  diet = new Diet("Wheat-free","No wheat ingredients.");
  dietList.add(diet);
 
  //create an ArrayAdaptar from the String Array
  dataAdapter = new MyCustomAdapter(this,
    R.layout.diet_info, dietList);
  ListView listView = (ListView) findViewById(R.id.listView1);
  // Assign adapter to ListView
  listView.setAdapter(dataAdapter);
 
 
  listView.setOnItemClickListener(new OnItemClickListener() {
   public void onItemClick(AdapterView<?> parent, View view,
     int position, long id) {
    // When clicked, show a toast with the TextView text
    Diet diet = (Diet) parent.getItemAtPosition(position);
    Toast.makeText(getApplicationContext(),
      "Clicked on Row: " + diet.getDescription(), 
      Toast.LENGTH_LONG).show();
   }
  });
 
 }
 
 private class MyCustomAdapter extends ArrayAdapter<Diet> {
 
  private ArrayList<Diet> dietList;
 
  public MyCustomAdapter(Context context, int textViewResourceId, 
   ArrayList<Diet> dietList){ 
	  super(context, textViewResourceId, dietList);
   this.dietList = new ArrayList<Diet>();
   this.dietList.addAll(dietList);
  }
 
  private class ViewHolder {
   TextView code;
   CheckBox name;
  }
 
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
 
   ViewHolder holder = null;
   Log.v("ConvertView", String.valueOf(position));
 
   if (convertView == null) {
   LayoutInflater vi = (LayoutInflater)getSystemService(
     Context.LAYOUT_INFLATER_SERVICE);
   //convertView = vi.inflate(R.layout.diet_info, null);
   convertView = vi.inflate(R.layout.checkbox_layout, parent, false);
 
   holder = new ViewHolder();
   holder.code = (TextView) convertView.findViewById(R.id.code);
   holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
   convertView.setTag(holder);
 
    holder.name.setOnClickListener( new View.OnClickListener() {  
     public void onClick(View v) {  
      CheckBox cb = (CheckBox) v ;  
      Diet diet = (Diet) cb.getTag();  
      Toast.makeText(getApplicationContext(),
       "Clicked on Checkbox: " + cb.getText() +
       " is " + cb.isChecked(), 
       Toast.LENGTH_LONG).show();
      diet.setSelected(cb.isChecked());
     }  
    });  
   } 
   else {
    holder = (ViewHolder) convertView.getTag();
   }
 
   Diet diet = dietList.get(position);
   holder.code.setText(" (" +  diet.getName() + ")");
   holder.name.setText(diet.getDescription());
   holder.name.setChecked(diet.isSelected());
   holder.name.setTag(diet);
 
   return convertView;
 
  }
 
 }
 
 private void checkButtonClick() {
 
 
  Button myButton = (Button) findViewById(R.id.askWatson);
  myButton.setOnClickListener(new OnClickListener() {
 
   public void onClick(View v) {
 
    StringBuffer responseText = new StringBuffer();
    responseText.append("The following were selected...\n");
 
    ArrayList<Diet> dietList = dataAdapter.dietList;
    for(int i=0;i<dietList.size();i++){
     Diet diet = dietList.get(i);
     if(diet.isSelected()){
      responseText.append("\n" + diet.getDescription());
     }
    }
 
    Toast.makeText(getApplicationContext(),
      responseText, Toast.LENGTH_LONG).show();
 
   }
  });
 
 }
 
}