package com.example.tpatel1474.finalproject;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateProduct extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText txtName,txtCuisine,txtPrice,txtId;
    Button btnCreate,btnView,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        myDb = new DatabaseHelper(this);

        txtName =(EditText)findViewById(R.id.txtName);
        txtCuisine =(EditText)findViewById(R.id.txtCuisine);
        txtPrice =(EditText)findViewById(R.id.txtPrice);
        txtId =(EditText)findViewById(R.id.txtId);
        btnCreate =(Button)findViewById(R.id.btnCreate);
        btnView=(Button)findViewById(R.id.btnView);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);

        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
    }
    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(txtId.getText().toString(),
                        txtName.getText().toString(),
                        txtCuisine.getText().toString(),txtPrice.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(CreateProduct.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CreateProduct.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(txtId.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(CreateProduct.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CreateProduct.this,"Data not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void ViewAll(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Cursor res=   myDb.getAllData();
                if(res.getCount() == 0) {

                   showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Cuisine :"+ res.getString(2)+"\n");
                    buffer.append("Price :"+ res.getString(3)+"\n\n");
                }


              showMessage("Data",buffer.toString());
            }

        });
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void AddData(){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isInserted =  myDb.insertData(txtName.getText().toString(),
                        txtCuisine.getText().toString(),
                        txtPrice.getText().toString());

                if(isInserted == true)
                    Toast.makeText(CreateProduct.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CreateProduct.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

  
}
