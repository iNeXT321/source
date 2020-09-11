package com.upeng.test;

public class Demo {

    public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {
        if (first) {
            first = false;

    /* TODO: Your code here. (Using info fields)

    FieldHelper infoField = get(Fields.Info, "info_field_name");

    RowSet infoStream = findInfoRowSet("info_stream_tag");

    Object[] infoRow = null;

    int infoRowCount = 0;

    // Read all rows from info step before calling getRow() method, which returns first row from any
    // input rowset. As rowMeta for info and input steps varies getRow() can lead to errors.
    while((infoRow = getRowFrom(infoStream)) != null){

      // do something with info data
      infoRowCount++;
    }
    */
        }

        Object[] r = getRow();

        if (r == null) {
            setOutputDone();
            return false;
        }

        // It is always safest to call createOutputRow() to ensure that your output row's Object[] is large
        // enough to handle any new fields you are creating in this step.
        r = createOutputRow(r, data.outputRowMeta.size());

  /* TODO: Your code here. (See Sample)

  // Get the value from an input field
  String foobar = get(Fields.In, "a_fieldname").getString(r);

  foobar += "bar";

  // Set a value in a new output field
  get(Fields.Out, "output_fieldname").setValue(r, foobar);

  */
        // Send the row on to the next step.

        Long custId = Long.valueOf(get(Fields.In, "vc_custid").getString(r));
        int tableIndex = new Long((custId%2)).intValue();
        //分表
        get(Fields.Out, "table_name").setValue(r, "t_acco_custacco"+"_"+tableIndex);

        //分库
        long num = custId - 100100000000L;
        if(num > 5000000L) {
            get(Fields.Out, "db").setValue(r, "0");
        }else{
            get(Fields.Out, "db").setValue(r, "1");
        }

        String seq = get(Fields.In, "seq").getString(r);
        get(Fields.Out, "p_key").setValue(r, seq);


        putRow(data.outputRowMeta, r);









        putRow(data.outputRowMeta, r);

        return true;
    }

}
