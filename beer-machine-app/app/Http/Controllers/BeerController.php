<?php

namespace App\Http\Controllers;

use Illuminate\Bus\Batch;
use Illuminate\Http\Request;

class BeerController extends Controller
{
    public function changeBatch(Request $request){

        $validate = $request->validate([
            'amount' => ['required', 'integer', 'between:1,65535'],
            'type' => ['required', 'integer', 'between:0,5']
        ]);

        $batch = new Batch();

        $batch->amount = $request->amount;
        $batch->type = $request->type;

        $batch->save();




    }
}
