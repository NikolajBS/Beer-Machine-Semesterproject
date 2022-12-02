<?php


use App\Http\Controllers\ProductController;
use App\Http\Resources\UserResource;
use App\Http\Controllers\Controller;

use App\Models\Inventory;
use Illuminate\Support\Facades\Route;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
Route::view('home','home')->name('home');


//Route::get('/test', function () {
//    return new UserResource(Inventory::find(1));
//});
Route::get('register', [ProductController::class, 'index'])->name('submit');
Route::post('register',[ProductController::class,'store'])->name('store');

Route::get('data',function (){
    return session()->pull('product');
});

//Route::get('elements',function (){
//    return Inventory::all();
//
//});
