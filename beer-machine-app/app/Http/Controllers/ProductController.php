<?php

namespace App\Http\Controllers;

use App\Models\Batch;
use Illuminate\Http\Request;

class ProductController extends Controller
{

    public function index(){
        return view('create');
    }
    public function store(Request $request){
        //validation
        $request->validate(['type'=>'required|between:0,5',
            'amount'=>'required|min:1|numeric|']);

        // create product in DB
        $batch = new Batch();
        $batch->type = request('type');
        $batch->amount = request('amount');
        $batch->save();

        $data = $batch->only('id','type','amount');
        $json = json_encode($data);

        session()->put('product',$json);
        return redirect()->route('home');
    }
}
