<?php


use App\Http\Controllers\ProductController;
use App\Http\Resources\UserResource;
use App\Http\Controllers\Controller;

use App\Models\Batch;
use App\Models\Inventory;
use Illuminate\Support\Facades\Route;


/*
|--------------------------------------------------------------------------
| Web Routes//Route::get('elements',function (){
//    return Inventory::all();
//
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('home', [ProductController::class,'index'])->name('home');

Route::get('/test', function () {
    return new UserResource(Batch::find(1));
});
Route::get('register', [ProductController::class, 'create'])->name('submit');
Route::post('register',[ProductController::class,'store'])->name('store');

Route::post('test123',function (){
    return view('home');
});
Route::get('mytest',[ProductController::class,'testing']);

